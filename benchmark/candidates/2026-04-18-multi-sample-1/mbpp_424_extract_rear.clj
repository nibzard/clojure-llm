(defn extract_rear
  "	Write a function to extract only the rear index element of each string in the given vector."
  [test_tuple]
  (mapv #(when (seq %) (subs % (dec (count %)))) test_tuple))