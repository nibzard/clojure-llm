(defn tuple_str_int
  "	Write a function to convert vector string to integer vector."
  [test_str]
  (vec (map #(Integer/parseInt %) test_str)))