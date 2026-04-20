(defn extract_freq
  "	Write a function to extract the number of unique vectors in the given list."
  [test_list]
  (count (set (filter vector? test_list))))