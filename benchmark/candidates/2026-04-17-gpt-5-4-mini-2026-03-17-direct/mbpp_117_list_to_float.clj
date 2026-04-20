(defn list_to_float
  "	Write a function to convert all possible convertible elements in a list of lists to floats."
  [test_list]
  (mapv
   (fn [inner]
     (mapv
      (fn [x]
        (try
          (float x)
          (catch Exception _ x)))
      inner))
   test_list))