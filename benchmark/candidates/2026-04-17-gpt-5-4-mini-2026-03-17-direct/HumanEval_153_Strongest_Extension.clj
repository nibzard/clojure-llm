(defn Strongest_Extension
  [class_name extensions]
  (let [strength (fn [s]
                   (reduce (fn [acc ch]
                             (cond
                               (Character/isUpperCase ^Character ch) (inc acc)
                               (Character/isLowerCase ^Character ch) (dec acc)
                               :else acc))
                           0
                           s))
        best (first (reduce (fn [[best-ext best-score] ext]
                              (let [score (strength ext)]
                                (if (> score best-score)
                                  [ext score]
                                  [best-ext best-score])))
                            [nil Long/MIN_VALUE]
                            extensions))]
    (str class_name "." best)))