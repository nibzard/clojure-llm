(defn problem-reimplement-trampoline
  [a & b]
  (loop [result (apply a b)]
    (if (fn? result)
      (recur (result))
      result)))