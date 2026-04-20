(defn list_to_float
  "Write a function to convert all possible convertible elements in a list of lists to floats."
  [test_list]
  (letfn [(convert-value [v]
            (cond
              (number? v) (float v)
              (string? v) (try
                            (Float/parseFloat v)
                            (catch NumberFormatException _ v))
              :else v))]
    (map (fn [inner-list]
           (map convert-value inner-list))
         test_list)))