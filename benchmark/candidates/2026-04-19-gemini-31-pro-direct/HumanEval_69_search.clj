(defn search [lst]
  (let [valid (for [[k v] (frequencies lst)
                    :when (and (pos? k) (>= v k))]
                k)]
    (if (seq valid)
      (apply max valid)
      -1)))