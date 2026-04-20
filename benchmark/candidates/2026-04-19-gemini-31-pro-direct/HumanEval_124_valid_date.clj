(defn valid_date [date]
  (if (empty? date)
    false
    (if-let [[_ m-str d-str y-str] (re-matches #"(\d{2})-(\d{2})-(\d{4})" date)]
      (let [m (Integer/parseInt m-str)
            d (Integer/parseInt d-str)]
        (and (<= 1 m 12)
             (cond
               (#{1 3 5 7 8 10 12} m) (<= 1 d 31)
               (#{4 6 9 11} m) (<= 1 d 30)
               (= 2 m) (<= 1 d 29)
               :else false)))
      false)))