(defn Strongest_Extension [class_name extensions]
  (let [calc-strength (fn [s]
                        (- (count (filter #(Character/isUpperCase %) s))
                           (count (filter #(Character/isLowerCase %) s))))
        strongest (reduce (fn [best ext]
                            (if (> (second ext) (second best))
                              ext
                              best))
                          (map (juxt identity calc-strength) extensions))]
    (str class_name "." (first strongest))))