(defn merge_dictionaries_three
  "	Write a function to merge three dictionaries into a single map."
  [dict1 dict2 dict3]
  (merge (or dict1 {}) (or dict2 {}) (or dict3 {})))