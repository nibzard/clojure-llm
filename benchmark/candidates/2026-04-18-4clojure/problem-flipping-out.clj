(defn problem-flipping-out [a]
  (fn [& args]
    (apply a (reverse args))))