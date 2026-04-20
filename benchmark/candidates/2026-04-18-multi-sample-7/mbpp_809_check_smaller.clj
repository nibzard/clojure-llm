(defn check_smaller
  "	Write a function to check if each element of second vector is smaller than its corresponding element in the first vector."
  [test_tup1 test_tup2]
  (and (= (count test_tup1) (count test_tup2))
       (every? true? (map < test_tup2 test_tup1))))