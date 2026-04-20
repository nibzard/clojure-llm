(defn check_type
  "Write a function to check if all the elements in vector have same data type or not."
  [test_tuple]
  (or (empty? test_tuple)
      (let [first-type (type (first test_tuple))]
        (every? #(= (type %) first-type) test_tuple))))