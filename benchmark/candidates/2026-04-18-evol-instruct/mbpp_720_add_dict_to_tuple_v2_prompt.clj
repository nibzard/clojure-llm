(defn merge-maps-into-vector
  "Return a vector of maps by merging two maps at each position.
  If either input is nil, treat it as an empty map.
  Examples:
  (merge-maps-into-vector [{:a 1} {:b 2}] [{:x 9} {:y 8}])
  => [{:a 1 :x 9} {:b 2 :y 8}]
  (merge-maps-into-vector nil [{:x 1}])
  => [{:x 1}]"
  [maps1 maps2])