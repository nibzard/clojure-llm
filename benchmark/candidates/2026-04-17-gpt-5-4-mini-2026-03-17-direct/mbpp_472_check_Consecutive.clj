(defn check_Consecutive
  "	Write a cljthon function to check whether the given list contains consecutive numbers or not."
  [l]
  (let [xs (sort l)]
    (and (seq xs)
         (every? true?
                 (map = (rest xs) (map inc xs))))))