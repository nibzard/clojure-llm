(defn list_to_float
  "Write a function to convert all possible convertible elements in a list of lists to floats."
  [test_list]
  (let [to-float (fn [x]
                   (try
                     (if (string? x)
                       (Float/parseFloat x)
                       (float x))
                     (catch Exception _ x)))]
    (map (fn [sublist]
           (map to-float sublist))
         test_list)))