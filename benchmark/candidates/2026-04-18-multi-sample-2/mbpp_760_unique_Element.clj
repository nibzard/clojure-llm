(defn unique_Element
  "	Write a cljthon function to check whether a list of numbers contains only one distinct element or not."
  [arr]
  (cond
    (nil? arr) false
    (empty? arr) false
    :else (= 1 (count (distinct arr)))))