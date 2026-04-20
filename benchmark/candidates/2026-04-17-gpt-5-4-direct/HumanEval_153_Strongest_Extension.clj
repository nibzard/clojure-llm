(defn Strongest_Extension
  "You will be given the name of a class (a string) and a list of extensions.
  The extensions are to be used to load additional classes to the class. The
  strength of the extension is as follows: Let CAP be the number of the uppercase
  letters in the extension's name, and let SM be the number of lowercase letters
  in the extension's name, the strength is given by the fraction CAP - SM.
  You should find the strongest extension and return a string in this
  format: ClassName.StrongestExtensionName.
  If there are two or more extensions with the same strength, you should
  choose the one that comes first in the list."
  [class_name extensions]
  (letfn [(strength [s]
            (reduce
             (fn [acc ch]
               (+ acc
                  (cond
                    (Character/isUpperCase ch) 1
                    (Character/isLowerCase ch) -1
                    :else 0)))
             0
             s))]
    (str class_name "."
         (first
          (reduce
           (fn [[best-ext best-score] ext]
             (let [score (strength ext)]
               (if (> score best-score)
                 [ext score]
                 [best-ext best-score])))
           [(first extensions) (strength (first extensions))]
           (rest extensions))))))