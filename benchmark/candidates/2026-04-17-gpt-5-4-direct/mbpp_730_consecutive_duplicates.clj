(defn consecutive_duplicates
  "	Write a function to remove consecutive duplicates of a given list."
  [nums]
  (when (seq nums)
    (reduce (fn [acc x]
              (if (= x (peek acc))
                acc
                (conj acc x)))
            []
            nums)))