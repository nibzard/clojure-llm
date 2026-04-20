(defn list_to_float
  "	Write a function to convert all possible convertible elements in a list of lists to floats."
  [test_list]
  (letfn [(to-float [x]
            (cond
              (number? x) (double x)
              (string? x) (try
                            (Double/parseDouble x)
                            (catch Exception _ x))
              :else x))]
    (if (sequential? test_list)
      (mapv (fn [sub]
              (if (sequential? sub)
                (mapv to-float sub)
                (to-float sub)))
            test_list)
      [])))