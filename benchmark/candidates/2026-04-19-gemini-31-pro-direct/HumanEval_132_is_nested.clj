(defn is_nested [s]
  (let [first-open (.indexOf s "[")
        last-close (.lastIndexOf s "]")]
    (if (and (>= first-open 0) (>= last-close 0) (< first-open last-close))
      (let [second-open (.indexOf s "[" (inc first-open))
            second-last-close (.lastIndexOf s "]" (dec last-close))]
        (boolean (and (>= second-open 0)
                      (>= second-last-close 0)
                      (< second-open second-last-close))))
      false)))