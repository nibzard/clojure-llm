(defn extract_rear
  "	Write a function to extract only the rear index element of each string in the given vector."
  [test_tuple]
  (mapv (fn [s]
          (when (seq s)
            (subs s (dec (count s)))))
        test_tuple))