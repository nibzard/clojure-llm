(defn extract_even
  "	Write a function to remove uneven elements in the nested mixed vector."
  [test_tuple]
  (cond
    (vector? test_tuple)
    (->> test_tuple
         (map extract_even)
         (remove nil?)
         vec)

    (and (number? test_tuple) (even? test_tuple))
    test_tuple

    :else
    nil))