(defn check_Consecutive
  "Write a cljthon function to check whether the given list contains consecutive numbers or not."
  [l]
  (if (empty? l)
    false
    (let [sorted (sort l)]
      (= sorted (range (first sorted) (+ (first sorted) (count sorted)))))))