(defn list_to_float
  "	Write a function to convert all possible convertible elements in a list of lists to floats."
  [test_list]
  (letfn [(to-float [x]
            (cond
              (float? x) x
              (number? x) (double x)
              (string? x) (try
                            (Double/parseDouble x)
                            (catch Exception _ x))
              :else x))]
    (if (nil? test_list)
      nil
      (mapv (fn [sub]
              (if (sequential? sub)
                (mapv to-float sub)
                sub))
            test_list))))