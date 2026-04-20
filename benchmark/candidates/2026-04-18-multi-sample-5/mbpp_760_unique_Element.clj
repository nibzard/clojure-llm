(defn unique_Element
  "	Write a cljthon function to check whether a list of numbers contains only one distinct element or not."
  [arr]
  (or (empty? arr)
      (apply = arr)))