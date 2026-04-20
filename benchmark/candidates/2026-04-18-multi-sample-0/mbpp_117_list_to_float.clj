(defn list_to_float
  "	Write a function to convert all possible convertible elements in a list of lists to floats."
  [test_list]
  (letfn [(convert [x]
            (cond
              (number? x) (double x)
              (string? x) (try
                            (Double/parseDouble x)
                            (catch Exception _ x))
              :else x))]
    (mapv (fn [sub]
            (if (sequential? sub)
              (mapv convert sub)
              sub))
          (or test_list []))))