(defn tuple_to_int
  "	Write a function to convert a given vector of positive integers into a single integer."
  [nums]
  (let [s (apply str (or nums []))]
    (if (empty? s) 0 (Long/parseLong s))))