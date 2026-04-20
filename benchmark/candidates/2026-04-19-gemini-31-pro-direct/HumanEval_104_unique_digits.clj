(defn unique_digits [x]
  (->> x
       (filter #(nil? (re-find #"[02468]" (str %))))
       sort
       vec))