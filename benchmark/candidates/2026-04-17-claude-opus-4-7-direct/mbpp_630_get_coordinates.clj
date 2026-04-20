(defn get_coordinates
  "Write a function to extract all the adjacent coordinates of the given coordinate vector."
  [test_tup]
  (let [deltas [-1 0 1]
        xs (first test_tup)
        ys (second test_tup)]
    (for [dx deltas
          dy deltas
          :when (not= 0 dx dy)]
      [(+ xs dx) (+ ys dy)])))