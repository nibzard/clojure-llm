(defn valid_date
  [date]
  (boolean
   (when (and (string? date) (not (clojure.string/blank? date)))
     (let [[mm dd yyyy :as parts] (clojure.string/split date #"-")]
       (when (= 3 (count parts))
         (let [parse-int #(try (Integer/parseInt %) (catch Exception _ nil))
               m (parse-int mm)
               d (parse-int dd)
               y (parse-int yyyy)
               max-day (case m
                         (1 3 5 7 8 10 12) 31
                         (4 6 9 11) 30
                         2 29
                         nil)]
           (and m d y
                (= (count mm) 2)
                (= (count dd) 2)
                (= (count yyyy) 4)
                (<= 1 m 12)
                (<= 1 d max-day)))))))