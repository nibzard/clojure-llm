(defn Strongest_Extension
  "Finds the strongest extension based on the formula CAP - SM and returns
  a string in the format ClassName.StrongestExtensionName."
  [class_name extensions]
  (if (empty? extensions)
    (str class_name ".")
    (let [strength (fn [s]
                     (let [caps (count (filter #(Character/isUpperCase %) s))
                           lowers (count (filter #(Character/isLowerCase %) s))]
                       (- caps lowers)))
          strongest (apply max-key strength extensions)]
      (str class_name "." strongest))))