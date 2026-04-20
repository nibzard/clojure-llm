(defn extract_rear
  "Write a function to extract only the rear index element of each string in the given vector."
  [test_tuple]
  (vec
   (map (fn [s]
          (when (and (string? s) (seq s))
            (subs s (dec (count s)))))
        test_tuple)))