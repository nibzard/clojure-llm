(defn min_val
  [listval]
  (let [nums (filter number? listval)]
    (when (seq nums)
      (apply min nums))))