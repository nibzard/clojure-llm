(defn get_coordinates
  "	Write a function to extract all the adjacent coordinates of the given coordinate vector."
  [test_tup]
  (let [[x y] test_tup]
    [[(dec x) (dec y)]
     [(dec x) y]
     [(dec x) (inc y)]
     [x (dec y)]
     [x (inc y)]
     [(inc x) (dec y)]
     [(inc x) y]
     [(inc x) (inc y)]]))