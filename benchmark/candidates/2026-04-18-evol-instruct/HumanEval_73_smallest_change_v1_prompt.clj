(defn normalize-map
  "Given a map m, return a new map where:
   - nil keys are ignored
   - values are converted to lowercase strings
   - if a key appears multiple times after normalization (for example, \"A\" and :a),
     keep the last encountered value
   - preserve entries with nil values by converting them to \"nil\"

   Examples:
   >>> (normalize-map {\"A\" \"HELLO\" :b \"WORLD\" nil \"SKIP\"})
   {\"A\" \"hello\", :b \"world\"}
   >>> (normalize-map {:X nil, \"x\" \"UP\", :y \"MiXeD\"})
   {\"x\" \"up\", :y \"mixed\"}
   >>> (normalize-map {})
   {}"
  [m])