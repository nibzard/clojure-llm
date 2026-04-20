(defn valid_date
  [date]
  (if (or (nil? date) (empty? date))
    false
    (let [parts (clojure.string/split date #"-")]
      (if (not= (count parts) 3)
        false
        (let [[month day year] parts]
          (if (and (re-matches #"\d{2}" month)
                   (re-matches #"\d{2}" day)
                   (re-matches #"\d{4}" year))
            (let [m (Integer/parseInt month)
                  d (Integer/parseInt day)]
              (if (and (>= m 1) (<= m 12))
                (cond
                  (#{1 3 5 7 8 10 12} m) (and (>= d 1) (<= d 31))
                  (#{4 6 9 11} m) (and (>= d 1) (<= d 30))
                  (= m 2) (and (>= d 1) (<= d 29))
                  :else false)
                false))
            false))))))