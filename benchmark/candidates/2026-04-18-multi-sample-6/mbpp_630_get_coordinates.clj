(defn get_coordinates
  "	Write a function to extract all the adjacent coordinates of the given coordinate vector."
  [test_tup]
  (when (and (vector? test_tup)
             (= 2 (count test_tup))
             (every? number? test_tup))
    (let [[x y] test_tup]
      [[(dec x) (dec y)]
       [x       (dec y)]
       [(inc x) (dec y)]
       [(dec x) y]
       [(inc x) y]
       [(dec x) (inc y)]
       [x       (inc y)]
       [(inc x) (inc y)]])))