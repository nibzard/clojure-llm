(defn encrypt
  [s]
  (let [shift 4
        lower-a (int \a)
        lower-z (int \z)
        upper-a (int \A)
        upper-z (int \Z)]
    (apply str
           (map (fn [ch]
                  (cond
                    (<= lower-a (int ch) lower-z)
                    (char (+ lower-a (mod (+ (- (int ch) lower-a) shift) 26)))

                    (<= upper-a (int ch) upper-z)
                    (char (+ upper-a (mod (+ (- (int ch) upper-a) shift) 26)))

                    :else ch))
                s))))