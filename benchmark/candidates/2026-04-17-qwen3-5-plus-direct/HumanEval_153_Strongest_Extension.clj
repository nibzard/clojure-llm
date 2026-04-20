(defn Strongest_Extension
  [class_name extensions]
  (let [strength (fn [ext]
                   (let [caps (count (filter #(Character/isUpperCase %) ext))
                         smalls (count (filter #(Character/isLowerCase %) ext))]
                     (- caps smalls)))
        strongest (first (apply max-key strength extensions))]
    (str class_name "." strongest)))