(defn tuple_str_int
  "	Write a function to convert vector string to integer vector."
  [test_str]
  (cond
    (nil? test_str) nil
    (empty? test_str) []
    :else (mapv #(Integer/parseInt %) test_str)))