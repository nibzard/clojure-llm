(defn check_type
  "Write a function to check if all the elements in vector have same data type or not."
  [test_tuple]
  (if (empty? test_tuple)
    true
    (let [first-type (type (first test_tuple))]
      (every? #(= first-type (type %)) test_tuple))))