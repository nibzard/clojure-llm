(defn extract_freq
  "Write a function to extract the number of unique vectors in the given list."
  [test_list]
  (if (seq? test_list)
    (count (filter vector? (set test_list)))
    0))