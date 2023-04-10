using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
public class SceneSwitch : MonoBehaviour
{
    
    public float timeRemaining = 180;
    public bool ending = false;

    // Start is called before the first frame update
    void Start()
    {
       
    }



    // Update is called once per frame
    void Update()
    {

        if (SceneManager.GetActiveScene() == SceneManager.GetSceneByName("MainMenuScene")) 
        {
            if (Input.GetKeyDown(KeyCode.Return))
            {
                
                SceneManager.LoadScene(1);
            }

        }

        //check if someone is playing
        if (Input.anyKey && (SceneManager.GetActiveScene() == SceneManager.GetSceneByName("GroveScene")))
        { 
            timeRemaining = 180;
        }

        if ((!ending) && SceneManager.GetActiveScene() == SceneManager.GetSceneByName("ConclusionSceneWolfLives"))
        {
            timeRemaining = 20;
            ending = true;
        }
        if ((!ending) && SceneManager.GetActiveScene() == SceneManager.GetSceneByName("ConclusionSceneWolfDies"))
        {
            timeRemaining = 20;
            ending = true;
        }
        //if too long passes, go back to the loading screen
        if (timeRemaining > 0)
        {
            timeRemaining -= Time.deltaTime;
        }
        else
        {
            SceneManager.LoadScene(0);
        }
        print(timeRemaining);
    }
}
