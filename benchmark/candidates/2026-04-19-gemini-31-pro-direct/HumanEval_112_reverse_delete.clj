(defn reverse_delete [s c]
  (let [c-set (set c)
        result (apply str (remove c-set s))]
    [result (= result (clojure.string/reverse result))]))