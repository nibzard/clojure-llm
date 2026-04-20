(defn extract_freq
  "	Write a function to extract the number of unique vectors in the given list."
  [test_list]
  (->> test_list
       (filter vector?)
       set
       count))