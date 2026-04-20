(defn get_coordinates
  "	Write a function to extract all the adjacent coordinates of the given coordinate vector."
  [test_tup]
  (let [[x y] test_tup]
    [[(dec x) y]
     [(inc x) y]
     [x (dec y)]
     [x (inc y)]]))