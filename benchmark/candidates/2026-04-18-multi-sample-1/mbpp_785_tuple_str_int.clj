(defn tuple_str_int
  "	Write a function to convert vector string to integer vector."
  [test_str]
  (vec
   (map (fn [s]
          (cond
            (integer? s) s
            (string? s) (Integer/parseInt s)
            :else (Integer/parseInt (str s))))
        (or test_str []))))