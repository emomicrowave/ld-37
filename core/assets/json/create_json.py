import json

to_export = {
        "scarf":{
            "attributes":{
                "hipster":2,
                "bdsm":1
                }
            },
        "bdsm_harness":{
            "attributes":{
                "bdsm":3
                }
            }
}

with open("data.json", "w") as outfile:
    json.dump(to_export, outfile)

test = {}
with open("data.json", "r") as outfile:
    test = json.load(outfile)

for n in test["scarf"]["attributes"]: print("%s:%s" % (str(n), test["scarf"]["attributes"][n]))
