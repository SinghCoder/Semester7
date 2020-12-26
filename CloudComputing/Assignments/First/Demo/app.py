from flask import Flask, request
import json
import subprocess

app = Flask(__name__)

@app.route("/runquery", methods=['GET'])
def run_query():
    query = request.args.get('query')    
    response_data = {'Results' : {}, 'Error' : 'No Error'}
    return_status = 200
    if query is None:
        return_status = 400
        response_data['Error'] = 'Query parameter not found'

    else:
        print("Query received: ", query)
        hadoop_query = query.replace("'", "")
        subprocess.call(["java", "-jar", "HadoopQuery.jar", "data/input", "data/output", hadoop_query])
        subprocess.call(["java", "-jar", "SparkQuery.jar", query])
        subprocess.call("rm -r data/output", shell=True)
        # subprocess.call("rm *.json", shell=True)
        output = {}
        with open('HadoopResults.json') as f:
            d = json.load(f)
            for key, value in d.items():
                output[key] = value
        with open('SparkResults.json') as f:
            d = json.load(f)
            for key, value in d.items():
                output[key] = value
        response_data['Results'] = output


    response = app.response_class(response=json.dumps(response_data),
                                  status=return_status,
                                  mimetype='application/json')
    return response

app.run(debug = True) 