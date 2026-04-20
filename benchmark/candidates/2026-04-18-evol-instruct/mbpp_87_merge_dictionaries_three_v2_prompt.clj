(defn merge-streams-by-key
  "Merge two possibly infinite sequences of maps by key, combining values with max.
  If a key appears in only one map, keep its value.
  Example:
  (merge-streams-by-key [{:id 1 :v 3} {:id 2 :v 5}]
                        [{:id 1 :v 7} {:id 3 :v 4}])
  => {1 {:id 1 :v 7}, 2 {:id 2 :v 5}, 3 {:id 3 :v 4}}"
  [keyfn xs ys])