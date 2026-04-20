(defn check_dict_case
  [dict]
  (if (empty? dict)
    false
    (let [ks (keys dict)]
      (if (every? string? ks)
        (or (every? #(= % (clojure.string/lower-case %)) ks)
            (every? #(= % (clojure.string/upper-case %)) ks))
        false))))