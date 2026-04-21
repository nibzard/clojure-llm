(defn tuple_str_int
  "Write a function to convert vector string to integer vector."
  [test_str]
  (if test_str
    (vec (map #(Integer/parseInt %) (re-seq #"-?\d+" test_str)))
    []))