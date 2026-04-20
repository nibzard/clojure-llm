(defn split_Arr
  "Write a clojure function to split a list at the nth element and add the first part to the end."
  [l n]
  (let [[first-part second-part] (split-at n l)]
    (concat second-part first-part)))